<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\User;
use Auth;

class UserController extends Controller
{
    public function index(Request $request){
        return $request->user();
    }

    public function register(Request $request){
        $request->validate([
            'name' => 'required',
            'email' => 'required|email|unique:users',
            'password' => 'required|confirmed',
        ]);
        $result = User::create([
            'name' => $request->name,
            'email' => $request->email,
            'password' => bcrypt($request->password)
        ]);
        return $result;
    }

    public function login(Request $request){
        $crendentials=$request->validate([
            'email' => 'required|email',
            'password' => 'required',
        ]);
        if(Auth::attempt($crendentials)){
            $user=Auth::user();
            $token=md5(time()).'.'.md5($request->email);
            $user->forceFill([
                'api_token' => $token,
            ])->save();
            return response()->json([
                'token' => $token
            ]);
        }
        return response()->json([
            'message'=> 'Invalid API Token'
        ], 401);
    }
}
