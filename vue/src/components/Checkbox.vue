// 多选框组件
<template>
    <div style="width: 100%">
        <div style="display: flex;justify-content: center;">
            <div>请选择您的爱好：</div>
            <div class="check-list" v-for="(item,index) in checkList" :key="index">
                <input type="checkbox" :id="'check'+index" v-model="checkValue" :value="item">
                <label :for="'check'+index">{{item}}</label>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Checkbox",
        props : {
            checkList: Array,
        },
        data() {
            return {
                checkValue:[],
            }
        },
        watch: {
            checkValue(val){
                this.$emit("checked",val);
            }
        },
    }
</script>

<style scoped>
input{
    display: none;
}
label{
    position: relative;
    user-select: none;
}
label:before{
    border: 2px solid #0d5c73;
    width: 12px;
    height: 12px;
    content: "";
    position: absolute;
    left: -25px;
    top: 1px;
    border-radius: 50%;
}
input:checked+label:before{
    background-color: #c5d8c5;
}
input:checked+label{
    color: #000000;
}
input:hover{

}
    span{
        color: #303133;
        margin-left: 2px;
    }
    .check-list{
        margin: 0 10px 0 25px;
    }
</style>
