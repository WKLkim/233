<template>
    <div style="
    background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
    background-size: cover;
    width: 100%;
    height: 100%;
    ">
        <form class="form-login"
              style="position: absolute;top: 50%;left: 50%;transform: translate(-50%,-50%);
                     width: 20%;height: 30%;background-color: white;border-radius: 5%;
             ">
            <h3 style="margin-top: 5%">欢迎来到媚仁光超市！</h3>
            <p style="margin-top: 10%" id="username">
                请输入密码：
                <input type="password" placeholder="" v-model="username" onkeypress="if(event.keyCode == 13) return false;" @keydown.enter="login()"/>
                </p>
            <el-button style="margin-top: 10%" type="primary" size="small" @click="login()">Login</el-button>
        </form>
    </div>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: "Form",
        data() {
            return{
                username: '',
            }
        },
        methods:{
            setCookie (cname, cvalue, exhours) {
                let date = new Date();
                date.setTime(date.getTime() + (exhours*60*60*1000));
                let expires = "expires=" + date.toGMTString();
                document.cookie = cname + "=" + cvalue + ";" +expires;
            },
            login(){
                console.log(this.username);
                request.get("/api/login",{
                    params: {
                        username: this.username,
                        currentPage: this.currentPage,
                        pageSize: this.pageSize,
                    }
                }).then(res => {
                    console.log(res.data.records);
                    if (res.data.records.length !== 0){
                        this.$message({
                            type: "success",
                            message: "登录成功"
                        });
                        this.setCookie('username',this.username,1);
                        this.$router.push("/home");
                    } else {
                        this.$message({
                            type: "error",
                            message: "密码错误！"
                        });
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>
