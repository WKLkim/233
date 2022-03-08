// 文本输入框
<template>
    <div style="width: 100%">
        <h3>请留言：</h3>
        <textarea
                style="width: 50%;min-height: 100px;background: none;outline: none;margin-top: 2%"
                v-model="textInput"
        ></textarea>
    </div>
</template>

<script>
    export default {
        name: "Textarea",
        watch: {
          textInput(val){
              this.$emit("textInput",val);
          }
        },
        data() {
            return {
                textInput:'',
            }
        }
    }
</script>

<style scoped>

</style>
