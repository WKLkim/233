// 导航栏，本项目无用
<template>
    <div style="width: 100%;display:flex;justify-content: space-between;background-color: #c5d8c5;padding: 0.5% 0 0.5% 0 ">
        <div
                v-for="(item,index) in navList"
                :key="index"
                style="margin-left: 5%;margin-right: 5%;width: 10%"
        >
            {{item}}
        </div>
        <div>
            <img style="position: absolute;left: 7%;width: 20px;" src="../assets/light.png">
        </div>
    </div>
</template>

<script>
    export default {
        name: "Tnav",
        data() {
            return {
                navList:['首页','活动','招聘','简介','校区'],
            }
        }
    }
</script>

<style scoped>

</style>
