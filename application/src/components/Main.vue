<template>
    <div id="main">
        <div id="header">
            <img src="https://www.998.com/Images/Home/Index/LeftTopTitle.png">
            <div style="display: inline-block;">
                <ul style="display: flex">
                    <li v-for="(i,n) in titles" :key="n">
                        <el-dropdown :hide-on-click="true">
                            <div :class="{select: n === choose}" @click="titleClick(n)">{{i}}</div>
                            <el-dropdown-menu slot="dropdown" v-if="subTitle[n].length> 0">
                                <el-dropdown-item v-for="(i2,n2) in subTitle[n]" :key="n2">
                                    {{i2}}
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </li>
                </ul>
            </div>
            <div id="login" v-if="!hasLogin">
                <div @click="login">登录</div>
                <div>|</div>
                <div @click="reg">注册</div>
            </div>
            <div v-else>
                <div>

                </div>
            </div>
        </div>

        <div id="body">
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Main",
        data: function () {
            return {
                hasLogin:false,
                titles: [
                    "首页", "酒店预订", "会员福利", "168商城", "新闻论坛", "格林公益", "人才"
                ],
                subTitle: [
                    [],
                    ["格林东方", "格美", "格雅", "格菲"],
                    [],
                    [],
                    [],
                    [],
                    []
                ],
                choose: 0
            }
        },
        methods: {
            titleClick: function (n) {
                this.choose = n;
                if (0 === n) {
                    this.$router.push("/home", null, null)
                }
            },
            login: function () {
                this.$router.push("/login", null, null)
            },
            reg: function () {
                this.$router.push("/reg", null, null)
            }
        },
        created(){
            window.console.log("Main created")
        },
        mounted(){
            window.console.log("Main mounted")
        }
    }
</script>

<style scoped>
    #header {
        padding: 15px;
        background-image: url('https://998.com/Images/Home/Index/Chairman/beijt.png');
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #header img {
        padding: 0 30px;
    }

    #header li div {
        color: white;
        padding: 0 0 5px 0;
        margin: 0 10px;
        font-size: 18px;
    }

    #login{
        margin-left: auto;
        margin-right: 20px;
    }

    #login div{
        display: inline;
        color: white;
        padding: 0 3px;
    }

    .select {
        border-bottom: 2px solid white;
    }
</style>