<template>
    <div id="main">
        <div :class="{shadow:edit}">
            <i class="el-icon-user" style="margin: 10px"></i>
            <span style="color: black">用户名</span>
            <input id ="i" type="text" style="border: none;padding: 5px 10px;outline: none" v-model="account" placeholder="请输入用户名" @blur="edit = false" @focus="edit = true">
        </div>
        <div :class="{shadow:edit2}">
            <i class="el-icon-key" style="margin: 10px"></i>
            <span style="color: black">  密  码  </span>
            <input type="text" style="border: none;padding: 5px 10px;outline: none" v-model="password" placeholder="请输入密码" @blur="edit2 = false" @focus="edit2 = true">
        </div>
        <el-button type="primary" @click="loginByAccount" style="margin-top: 40px">登录</el-button>
    </div>
</template>

<script>
    export default {
        name: "Login_Account",
        data: function () {
            return {
                account: "",
                password: "",
                edit:false,
                edit2:false
            }
        },
        mounted() {
            this.account = 'DQ2020';
            this.password = '123456';
        },
        methods: {
            loginByAccount: function () {
                const data = {"account": this.account, "pwd": this.password};
                this.$axios.post("/account/login", data)
                    .then((response) => {
                        const result = response.data;
                        if (result.code === 200) {
                            this.$store.commit("initUser", result.value);
                            this.$router.push('/', null);
                            this.$message({
                                message: "登录成功",
                                type: "success",
                                offset: 550
                            })
                        } else {
                            this.$message({
                                message: result.msg,
                                type: "success",
                                offset: 550
                            })
                        }
                    })
                    .catch((error) => {
                        alert(error.message)
                    });
            },
        },
        computed: {
        }
    }
</script>

<style scoped>
    #main {
        border: #e0d7d5 1px solid;
        border-top: transparent 1px solid;
        height: 240px;
        width: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 40px;
    }

    #main>div{
        padding: 5px 10px;
        border: darkgrey 1px solid;
        margin: 5px;
    }
    .shadow{
        box-shadow:0 0 3px 0 #cecbc7,0 0 6px 0 #e3d5de,0 0 6px 0 #eee6eb !important;
    }

    #main>div{
        margin-top: 20px;
    }
</style>