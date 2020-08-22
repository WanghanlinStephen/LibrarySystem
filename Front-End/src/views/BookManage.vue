<template>
    <div>

        <div class="searchBar">
            <el-input  placeholder="请输入内容" v-model="input" class="input-with-select">
                <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
            </el-input>
        </div>

        <el-table
                :data="tableData"
                border
                style="width: 70%">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="图书名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    >
                <template slot-scope="scope">
                    <el-button @click="detail(scope.row)" type="text" size="small">查看</el-button>
                    <el-button @click="edit(scope.row)" type="text" size="small">修改</el-button>
                    <el-button @click="deleteBook(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                input: '',
                pageSize:'1',
                total:'11',
                tableData: [{
                    id: 1,
                    name: '解忧杂货店',
                    author: '东野圭吾',
                    price:'10'
                }, {
                    id: 2,
                    name: '追风筝的人',
                    author: '卡勒德·胡赛尼',
                    price:'20'
                }, {
                    id: 3,
                    name: '人间失格',
                    author: '太宰治',
                    price:'10',
                }]
            }
        },

        methods: {
            deleteBook(row){
                const _this = this
                axios.delete('http://localhost:8181/book/deleteById/'+row.id).then(function(resp){
                    _this.$alert('《'+row.name+'》删除成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                this.$router.push({
                    path: '/update',
                    query:{
                        id:row.id
                    }
                })
            },
            detail(row){
                this.$router.push({
                    path: '/detail',
                    query:{
                        id:row.id
                    }
                })
            },
            page(currentPage){
                const _this = this
                axios.get('http://localhost:8181/book/findAll/'+(currentPage-1)+'/6').then(function(resp){
                    console.log(resp)
                    _this.tableData = resp.data.content
                    console.log(_this.tableData )
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })
            },
            search(){
                alert("I am working");
                const _this = this
                axios.get('http://localhost:8181/book/findByName/'+(this.input)).then(function(resp){
                    console.log(resp)
                    if(resp.data.content ==null){
                        _this.tableData = resp.data
                    }else{
                        _this.tableData = resp.data.content
                    }
                    console.log(_this.tableData )
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })
            }

        },


        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findAll/0/6').then(function(resp){
                console.log(resp)
                _this.tableData = resp.data.content
                console.log(_this.tableData )
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements
            })
        }
    }
</script>
<style>
    .el-select .el-input {
        width: 100px;
    }
    .searchBar{
        width: 80%;
        margin-bottom: 50px;
    }
    .input-with-select .el-input-group__prepend {
        background-color: #fff;
    }

</style>