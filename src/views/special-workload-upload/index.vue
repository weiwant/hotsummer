<template>
    <div id="special-workload-upload" class="app-right-wrapper">
        <OperationBar @showForm="(type) => { this.showForm = true; this.workloadTypeChosen = type }" />
        <WorkloadForm v-if="showForm" :type="this.workloadTypeChosen" @cancle='() => this.showForm = false' />
    </div>
</template>
<script>
import OperationBar from './components/OperationBar.vue'
import WorkloadForm from './components/WorkloadForm.vue'

export default {
    name: "UploadSpecialWorkload",
    components: {
        OperationBar,
        WorkloadForm
    },
    data() {
        return {
            showForm: false,
            workloadTypeChosen: 'BB1',
        }
    },
    methods: {

    }

}
</script>
<style scoped>

</style>
