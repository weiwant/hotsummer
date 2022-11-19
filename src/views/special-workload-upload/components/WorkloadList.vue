<template>
    <div id="specialworkload-upload-list">
        {{                                                this.$route.params.status                                                }}
    </div>
</template>
<script>
export default {
    name: "SpecialworkloadUploadList",
    data() {
        return {
            status: ''
        }
    }
}
</script>
<style scoped>

</style>
