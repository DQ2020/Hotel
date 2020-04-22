import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from "../components/login/Login"
import Reg from "../components/login/Reg"
import Login_Account from "../components/login/Login_Account"
import Login_Mobile from "../components/login/Login_Mobile"
import Home from "../components/home/Home"
import Vip from "../components/vip/Vip"
import Main from "../components/Main";

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: "/",
            name: "main",
            component: Main,
            children: [
                {
                    path: "/home",
                    name: "home",
                    component: Home
                },
                {
                    path: "/login",
                    name: "login",
                    component: Login,
                    redirect: "/login_account",
                    children: [
                        {
                            path: "/login_account",
                            name: "login_account",
                            component: Login_Account
                        },
                        {
                            path: "/login_mobile",
                            name: "login_mobile",
                            component: Login_Mobile
                        }
                    ]
                },
                {
                    path: "/reg",
                    name: "reg",
                    component: Reg
                },
                {
                    path: "/vip",
                    name: "vip",
                    component: Vip,
                }
            ]
        }
    ]
});


export default router;