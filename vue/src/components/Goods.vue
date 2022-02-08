<template>
    <div style="width: 100%;height: 100%">
        <div style="width: 100%;height: 100%;position: relative">
            <el-button type="primary" @click="add" size="small" style="position: relative;top: 4%;left: 15%">新增</el-button>
            <el-row :gutter="20" style="width: 20%;position: absolute;right: 11%;top: 4%">
                <el-input v-model="inputSearch" size="small" placeholder="搜索" >
                    <template #prefix>
                        <el-icon @click="search" class="el-input__icon"><search /></el-icon>
                    </template>
                </el-input>
            </el-row>
            <el-table :data="tableData" size="mini"  border style="position: absolute;right: 10%;top: 12%;width: 100%">
                <el-table-column prop="gid" label="商品编号"  />
                <el-table-column prop="gname" label="商品名称"  />
                <el-table-column prop="gprice" label="商品价格"/>
                <el-table-column prop="gnum" label="商品数量" />
                <el-table-column prop="gtext" label="商品描述" />
                <el-table-column label="商品操作">
                    <template #default="scope">
                        <el-button type="success" size="small" @click="edit(scope.row)">编辑</el-button>
                        <el-popconfirm title="确认删除吗？" @confirm="deleteRow(scope.row.gid)">
                            <template #reference>
                                <el-button type="danger" size="small" >删除</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
            <div class="demo-pagination-block" style="position: absolute;bottom: 1%;left: 25%">
                <span class="demonstration"></span>
                <el-pagination
                        v-model:currentPage="currentPage"
                        :page-sizes="[4, 8]"
                        :page-size="pageSize"
                        layout="total, sizes, prev, pager, next"
                        :total="total"
                        small
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                >
                </el-pagination>
            </div>
        </div>
        <el-dialog
                v-model="dialogVisible"
                title="请编辑"
                width="30%"
                :before-close="handleClose"
        >
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="商品编号">
                    <el-input v-model="form.gid"></el-input>
                </el-form-item>
                <el-form-item label="商品名称">
                    <el-input v-model="form.gname"></el-input>
                </el-form-item>
                <el-form-item label="商品价格">
                    <el-input v-model="form.gprice"></el-input>
                </el-form-item>
                <el-form-item label="商品数量">
                    <el-input v-model="form.gnum"></el-input>
                </el-form-item>
                <el-form-item label="商品描述">
                    <el-input v-model="form.gtext"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import request from "@/utils/request";
    import {Search} from '@element-plus/icons';
    export default {
        name: "Goods",
        components: {
            Search
        },
        watch: {
            inputSearch(){
                this.load();
            }
        },
        data() {
            return {
                form: {},
                dialogVisible: false,
                inputSearch: '',
                total: 0,
                currentPage: 1,
                pageSize: 8,
                tableData: [],
                addType: false,
                editType: false,
            }
        },
        created() {
            this.load()
        },
        methods: {
            load() {
                request.get("/api/goods",{
                    params: {
                        currentPage: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.inputSearch
                    }
                }).then(res => {
                    console.log(res);
                    this.tableData = res.data.records;
                    this.total = res.data.total;
                })
            },
            add() {
                this.dialogVisible = true;
                this.form = {
                    gid: 'g00',
                    gtext: '干净又卫生',
                };
                this.addType = true;
            },
            edit(val) {
                this.dialogVisible = true;
                this.form = val;
                this.editType = true;
            },
            save() {
                if (this.editType) {
                    request.put("/api/goods",this.form).then(res => {
                        if (res.code === '0'){
                            this.dialogVisible = false;
                            this.load();
                            this.editType = false;
                            this.$message({
                                type: "success",
                                message: "更新成功"
                            })
                        } else {
                            console.log(res);
                        }
                    });
                } else {
                    request.post("/api/goods",this.form).then(res => {
                        console.log(res);
                        if (res.code === '0'){
                            this.dialogVisible = false;
                            this.load();
                            this.addType = false;
                            this.$message({
                                type: "success",
                                message: "新增成功"
                            })
                        } else {
                            console.log(res);
                        }
                    });
                }
            },
            handleSizeChange(val) {             //改变每页数据条数
                this.pageSize = val;
                this.load()
            },
            handleCurrentChange(val) {          //改变当前页码
                this.currentPage = val;
                this.load()
            },
            search() {

            },
            deleteRow(val) {
                request.delete("/api/goods/" + val ).then(res => {
                    if (res.code === '0'){
                        this.load();
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else {
                        console.log(res);
                    }
                })
            },
        }
    }
</script>

<style scoped>

</style>
