<template>
    <div id="main">
        <el-image fit="contain" src="https://www.998.com/Images/Amendment/Login/bg_signin.png"/>
        <div id="body">
            <ul>
                <li :class="{select:select}" @click="selected">账号登录</li>
                <li :class="{select:!select}" @click="selected">手机登录</li>
            </ul>
            <keep-alive>
                <router-view></router-view>
            </keep-alive>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data: function () {
            return {
                select: true
            }
        },
        methods: {
            selected: function () {
                this.select = !this.select;
                if (this.select) {
                    this.$router.push("/login_account", null, null)
                } else {
                    this.$router.push("/login_mobile", null, null)
                }
            }
        }
    }
</script>

<style scoped>
    #main {
        display: flex;
        height: 80vh;
        align-items: center;
        justify-content: center;
    }

    #body {
        align-items: center;
        margin-left: 120px;
    }

    li {
        flex: 1;
        padding: 10px;
        text-align: center;
        color: #7bb915;
        background: #f2f2f2;
        border: #e0d7d5 1px solid;
        border-top: #7bb915 2px solid;

    }

    ul {
        display: flex;
    }

    ul li:first-child{
        border-left: #e0d7d5 1px solid;
    }

    ul li:last-child{
        border-right: #e0d7d5 1px solid;
    }

    .select {
        background: white;
        border-bottom: white;
        border-left: white;
        border-right: white;
    }
</style>