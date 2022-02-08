<template>
  <div class="home" style="height: 100%;width: 100%">
    <Nav>
    </Nav>
    <div style="display: flex;justify-content: start;height: calc(100% - 61px)">
      <el-row style="width: 25%;" class="tac">
        <el-col :span="12" style="background-color:#545c64;width: 100%">
          <el-menu
                  active-text-color="#ffd04b"
                  background-color="#545c64"
                  class="el-menu-vertical-demo"
                  default-active="1"
                  text-color="#fff"
          >
            <el-menu-item index="1" @click="showIfoG()">
              <el-icon><shopping-bag /></el-icon>
              <span>商品管理</span>
            </el-menu-item>
            <el-menu-item index="2" @click="showIfoE()">
              <el-icon><user-filled /></el-icon>
              <span>职工管理</span>
            </el-menu-item>
            <el-menu-item index="3" @click="showIfoP()">
              <el-icon><avatar /></el-icon>
              <span>客户管理</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
      <Goods v-if="showGoods"></Goods>
      <Employee v-if="showEmployee"></Employee>
      <Partner v-if="showPartner"></Partner>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Nav from '../components/nav.vue'
import request from "../utils/request";
import {ShoppingBag,UserFilled,Avatar} from '@element-plus/icons';
import Goods from "../components/Goods";
import Employee from "../components/Employee";
import Partner from "../components/Partner";

export default {
  name: 'Home',
  components: {
    Partner,
    Employee,
    Goods,
    Nav,ShoppingBag,UserFilled,Avatar,
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
      showGoods: true,
      showEmployee: false,
      showPartner: false,
    }
  },
  created() {
    let username = sessionStorage.getItem("username");          //是否已登录
    if (username != null) {
      this.load();
      console.log(username + '已登录');
    }
    else this.$router.push("/");
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
      this.load()
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
    showIfoG() {
      this.showGoods = true;
      this.showEmployee = false;
      this.showPartner = false;
    },
    showIfoE() {
      this.showGoods = false;
      this.showEmployee = true;
      this.showPartner = false;
    },
    showIfoP() {
      this.showGoods = false;
      this.showEmployee = false;
      this.showPartner = true;
    },
  }
}
</script>
