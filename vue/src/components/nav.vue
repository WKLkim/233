<template>
    <div >
        <el-menu
                class="el-menu-demo"
                mode="horizontal"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
                @select="handleSelect"
                style="display: flex;justify-content: right"

        >
            <span style="font-family: 微軟正黑體;position: absolute;left: 1%;color: #fff;font-size: 20px;top: 50%;transform: translate(0,-50%)" >媚仁光管理中心</span>
            <el-menu-item index="1" >
                <el-icon :size="20" :title="username">
                    <user/>
                </el-icon>
            </el-menu-item>
            <el-sub-menu index="2">
                <template #title>更多</template>
                <el-menu-item index="2-1" @click="email">联系我们</el-menu-item>
                <el-menu-item index="2-2" @click="exit()">退出系统</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>

<script>
    import {User} from '@element-plus/icons'
    export default {
        name: "nav",
        components: {
          User
        },
        data() {
          return {
              username: this.$store.state.username,
          }
        },
        methods: {
            email() {
                alert('1256174238@qq.com')
            },
            exit() {
                this.$router.push('/');
                // 删除所有cookie数据
                var keys=document.cookie.match(/[^ =;]+(?=)/g);
                if (keys) {
                    for (var i =  keys.length; i--;)
                    document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString()
                }   
            }
        }
    }
</script>

<style scoped>

</style>
