<template>
    <div style="padding: 40px">
        <el-row :gutter="80">
            <el-col :span="2">
                <el-button size="small" type="success" style="margin: 0 0 20px 0" @click="addAccount">添加</el-button>
            </el-col>
            <el-col :span="22">
                <el-col>
                    <el-input placeholder="请输入内容" v-model="search" size="small">
                        <el-button slot="append" icon="el-icon-search" @click="toSearch"></el-button>
                    </el-input>
                </el-col>
            </el-col>
        </el-row>

        <el-table
                stripe
                border
                :data="lists"
                style="width: 100%;box-sizing: border-box;">
            <el-table-column
                    prop="id"
                    label="ID"
                    width="60">
            </el-table-column>
            <el-table-column
                    prop="account"
                    label="账户">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.add" v-model="scope.row.account"></el-input>
                    <span v-else>{{scope.row.account}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.name"></el-input>
                    <span v-else>{{scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="tel"
                    label="电话">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.tel"></el-input>
                    <span v-else>{{scope.row.tel}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.age"></el-input>
                    <span v-else>{{scope.row.age}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.sex"></el-input>
                    <span v-else>{{scope.row.sex}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="score"
                    label="积分">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.score"></el-input>
                    <span v-else>{{scope.row.score}}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="密码"
                    prop="pwd">
                <template slot-scope="scope">
                    <el-input placeholder="请输入内容" v-if="scope.row.edit" v-model="scope.row.pwd"></el-input>
                    <span v-else>{{scope.row.pwd}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <div v-if="scope.row.enable === 1">
                        <div v-if="scope.row.edit === false">
                            <el-button type="text" size="small" @click="scope.row.edit = true">编辑</el-button>
                            <el-button type="text" size="small" @click="disableAccount(scope.row)">禁用</el-button>
                        </div>
                        <div v-else>
                            <el-button type="text" size="small" @click="complete(scope.row)">完成</el-button>
                            <el-button type="text" size="small" @click="reset(scope.row)">取消</el-button>
                        </div>
                    </div>
                    <el-button type="text" size="small" v-else @click="enableAcc(scope.row)">启用(已禁用)</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "Vip",
        data: function () {
            return {
                lists: null,
                search: ""
            }
        },
        beforeCreate: function () {
            this.$axios.get("/accounts")
                .then((response) => {
                    const result = response.data;
                    if (result.code === 200) {
                        let lists = result.value;
                        lists.forEach(element => {
                            element["edit"] = false
                        });
                        this.lists = lists;
                    } else {
                        this.$message({
                            message: result.msg,
                            type: "warning",
                            offset: 550
                        })
                    }
                })
                .catch((error) => {
                    alert(error.message)
                });
        },
        methods: {
            disableAccount: function (row) {
                this.$axios.get("/delAcc", {
                    params: {
                        "id": row.id
                    }
                })
                    .then((r) => {
                        this.$message({
                            message: r.data.msg,
                            type: "success",
                            offset: 550
                        });
                        if (r.data.code === 200) {
                            // 本页面刷新 效果不好
                            // this.$router.go(0)
                            row.enable = 0
                        }
                    })
            },
            enableAcc: function (row) {
                row.enable = 1;
                this.complete(row)
            },
            complete: function (row) {
                this.$axios.post("/updateAcc", row)
                    .then((res) => {
                        if (res.data.code === 200) {
                            row.edit = false;
                            row.add = 0;
                        }
                    })
                    .catch((e) => {
                        alert(e);
                    });
            },
            addAccount: function () {
                this.lists.push({
                    'id': this.lists[this.lists.length - 1].id + 1,
                    'edit': true,
                    'enable': 1,
                    "add": 1
                })
            },
            reset: function (row) {
                row.edit = false;
                if (row.add === 1) {
                    this.lists.splice(-1, 1);
                }
            },
            toSearch: function () {
                this.$axios.get("/accounts" + "/" + this.search).then((response) => {
                    const result = response.data;
                    if (result.code === 200) {
                        let lists = result.value;
                        lists.forEach(element => {
                            element["edit"] = false
                        });
                        this.lists = lists;
                    } else {
                        this.$message({
                            message: result.msg,
                            type: "warning",
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
</style>