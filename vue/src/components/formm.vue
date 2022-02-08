<template>
    <form>
        <el-popconfirm title="确认要提交吗？" @confirm="postForm">
            <template #reference>
                <button class="summit" type="button">summit</button>
            </template>
        </el-popconfirm>
        <div class="form-list" :style="{backgroundImage: 'url('+imgSrc[0].src+')'}">
            <form class="form-login" style="">
                <p id="username"><input type="text" placeholder="" v-model="form.username"></p>
                <p id="password_1"><input type="password" placeholder="" v-model="form.password"></p>
                <p id="password_2"><input type="password" placeholder="" v-model="pwAgain"></p>
                <p id="tip" v-if="show">两次输入的密码不同！</p>
            </form>
        </div>
        <div class="form-list" :style="{backgroundImage: 'url('+imgSrc[1].src+')'}">
            <select-city class="select-city" @selected="selected"></select-city>
        </div>
        <div class="form-list" :style="{backgroundImage: 'url('+imgSrc[2].src+')'}">
            <Checkbox class="check-box" :check-list="hobby" @checked="checked"></Checkbox>
        </div>
        <div class="form-list" :style="{backgroundImage: 'url('+imgSrc[3].src+')'}">
            <Textarea class="text-area" @textInput="textInput"></Textarea>
        </div>
    </form>
</template>

<script>
    import SelectCity from "./selectCity";
    import Checkbox from "./Checkbox";
    import Textarea from "./Textarea";
    import request from "@/utils/request";
    export default {
        name: "formm",
        components: {
            Textarea,
            Checkbox,
            SelectCity

        },
        watch: {
            pwAgain(val){
                if (this.form.password&&(val!=this.form.password)) this.show = true;
                else this.show = false;
            },
        },
        data() {
            return {
                pwAgain:'',
                show: false,
                imgSrc:[
                    {src:'https://img0.baidu.com/it/u=2416331936,3091408825&fm=26&fmt=auto'},
                    {src:'https://img0.baidu.com/it/u=1509308828,1650759786&fm=26&fmt=auto'},
                    {src:'https://img0.baidu.com/it/u=1775953437,2514648192&fm=26&fmt=auto'},
                    {src:'https://img1.baidu.com/it/u=2479789976,692425614&fm=26&fmt=auto'},
                ],
                select: { province: '广东省', city: '广州市', area: '海珠区' },
                hobby:['篮球','乒乓球','羽毛球','游泳'],
                form: {
                    username:'',
                    password:'',
                    checkvalue:[],
                    selectvalue:'',
                    text:'',
                }
            }
        },
        methods: {
            checked(val){
               this.form.checkvalue = val.join("、");
                // console.log(this.form.checkvalue);
            },
            selected(val){
                this.form.selectvalue = val;
                // console.log(this.form.selectvalue);
            },
            textInput(val){
                this.form.text = val;
                // console.log(this.form.text);
            },
            postForm(){
                if (this.show||!this.form.username||!this.form.password) {
                    this.$message({
                        type: "error",
                        message: "提交失败，请确认信息完整性"
                    });
                } else {
                    request.post("/api/login",this.form).then(res => {
                        console.log(res);
                        if (res.code === '0'){
                            this.$message({
                                type: "success",
                                message: "提交成功"
                            });
                            sessionStorage.setItem("username", this.form.username);
                            this.$router.push("/showperson");
                        }
                    });
                    console.log(this.form);
                }

            }
        }
    }
</script>

<style scoped>
form{
    display: flex;
    justify-content: start;
    flex-wrap: wrap;
}
    .form-list{
        margin: 2%;
        width: 46%;
        height: 41vh;
        background-size: cover;
        box-sizing: border-box;
        position: relative;
        z-index: 0;
        border-radius: 2%;
    }
    .form-list:after{
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        left: 0;
        top: 0;
        background: inherit;
        filter: blur(3px);
        z-index: -1;
    }
    .form-login p{
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        width: 100%;
    }

#username:before{
    content: '输入账号：';
}
#username{
    top: 35%;
}
#password_2{
    top: 65%;
}
#tip{
    color: #d54949;
    font-size: 8px;
    top: 75%;
    font-weight: bold;
}
#password_1:before{
    content: '输入密码：';
}
#password_2:before{
    content: '确认密码：';
}
#form-button{
    top: 80%;
}
.form-login input{
    border: none;
    border-bottom: 1px solid black;
    outline: none;
    font-size: 15px;
    background: none;
}
.form-login button{
    margin-left: 5%;
    width: 10%;
    padding-top: 5px;
    padding-bottom: 5px;
    background-color: #5c9da7;
    border: none;
    outline: none;
    cursor: pointer;
    color: white;
    border-radius: 10px;
}
.form-login button:hover{
    filter: brightness(1.2);
}
    .summit{
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        border-radius: 50%;
        width: 50px;
        height: 50px;
        border: none;
        cursor: pointer;
        background-color: #40e69b;
        color: white;
        box-shadow: 4px 3px 6px #888888;
    }
    .select-city,.check-box,.text-area{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
</style>
