<template>
    <div id="main">
        <el-input placeholder="请输入手机号" v-model="tel" clearable style="margin-top: 20px;width: 300px">
            <template slot="prepend">手机号:</template>
        </el-input>
        <el-input placeholder="验证码" v-model="code" clearable style="margin-top: 10px;width: 300px">
            <template slot="prepend">验证码:</template>
            <el-button slot="append" style="padding: 10px" @click="getCode">发送验证码</el-button>
        </el-input>
        <el-button type="primary" @click="loginByMobilePhone" style="margin-top: 40px">登录</el-button>
    </div>
</template>

<script>
    export default {
        name: "login_mobile",
        data: function () {
            return {
                tel: "",
                code: ""
            }
        },
        methods: {
            getCode: function () {
                const param = {
                    params: {
                        tel: this.tel
                    }
                };
                this.$axios.get("/getCode", param)
                    .then((response) => {
                        const result = response.data;
                        if (result.code === 200) {
                            this.$message({
                                message: result.value.code,
                                type: "success",
                                offset: 550
                            })
                        }
                    })
                    .catch((error) => {
                        alert(error.message)
                    });
            },
            loginByMobilePhone: function () {
                const param = {
                    tel: this.tel,
                    code: this.code
                };
                this.$axios.post("/loginByTel", param)
                    .then((response) => {
                        const result = response.data;
                        if (result.code === 200) {
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
            }
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

</style>