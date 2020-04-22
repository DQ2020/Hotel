<template>
    <div>

        <div class="shadow" style="height: 200px;width: 200px; background: transparent;margin: 10px">

        </div>
    </div>
</template>

<script>
    export default {
        name: "Reg",
        data:function () {
            return{
                account:""
            }
        }
    }
</script>

<style scoped>
    .shadow{
        box-shadow:0 0 3px 0 #cecbc7,0 0 6px 0 #e3d5de,0 0 6px 0 #eee6eb !important;
    }
</style>