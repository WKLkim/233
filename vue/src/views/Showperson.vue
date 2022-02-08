<template>
    <div class="container" style="width: 100%;height: 100vh">
        <div class="information">
            <h2 style="margin-top: 5%">您的信息</h2>
            <div style="margin-top: 15%">
                <p>账号：<span>{{dataIfo.username}}</span></p>
                <p>地址：<span>{{dataIfo.selectvalue}}</span></p>
                <p>爱好：<span>{{dataIfo.checkvalue}}</span></p>
                <p>留言：<span>{{dataIfo.text}}</span></p>
            </div>
        </div>
        <div>
            <a href="http://localhost:63342/test/vue/src/E4/e1/e1.html?_ijt=ulc511v7aaludt8oa7cdfb4vp5" style="text-decoration: none;color: black">下一页更精彩</a>
        </div>
    </div>
</template>

<script>
    import request from "@/utils/request";
    export default {
        name: "Showperson",
        data() {
            return {
                dataIfo: {}
            }
        },
        mounted() {
            let username = sessionStorage.getItem("username");
            console.log(username);
            request.get("/api/login",{
                params: {
                    username: username,
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                }
            }).then(res => {
                console.log(res.data.records);
                this.dataIfo = res.data.records[0];
            })
        }
    }
</script>

<style scoped>
.container{
    background-image: url("https://img2.baidu.com/it/u=2182069778,1274333682&fm=26&fmt=auto");
    background-repeat: no-repeat;
    background-size: cover;
    overflow: hidden;
}
    .information{
        background: white;
        border-radius: 10px;
        width: 30%;
        height: 70%;
        margin-top: 8%;
        margin-left: 35%;
        overflow: hidden;
    }
    p{
        margin-top: 5%;
    }
</style>
