<template>
  <div id="plain-table">
    <div class="mask" v-show="showLoading">
      <Loading />
    </div>
    <div class="table-wrapper">
      <transition name="fadeIn" mode="out-in">
        <table v-if='(dataArray.length != 0)'>
          <thead>
            <tr>
              <th v-for="header in headerArray" :key="header.key">{{ header.label }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(data, outerIndex) in dataArray" :key="data.id" @click="toggleHighlight(outerIndex)"
              :class="{ highlight: outerIndex == currenthighlightRowIndex }">
              <td v-for="header in headerArray">
                {{ data[header.key] }}
              </td>
            </tr>
          </tbody>
        </table>
        <NoDataMessage v-else />
      </transition>
    </div>
  </div>
</template>

<script>
import Loading from '@/components/Loading.vue';
import NoDataMessage from '@/components/NoDataMessage.vue'
export default {
  props: {
    headerArray: {
      type: Array,
      required: true,
    },
    dataArray: {
      type: Array,
      required: true,
    },
    showLoading: {
      type: Boolean,
      required: true
    }
  },
  components: { Loading, NoDataMessage },
  data() {
    return {
      currenthighlightRowIndex: null,
    }
  },
  methods: {
    toggleHighlight(outerIndex) {
      if (outerIndex == this.currenthighlightRowIndex) this.currenthighlightRowIndex = null;
      else this.currenthighlightRowIndex = outerIndex;
    }
  }
};
</script>

<style scoped lang="scss">
@import "@/style/variables.scss";
@import "@/style/table.scss";

#plain-table {
  position: relative;
}
</style>