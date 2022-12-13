<template>
  <div id="plain-table">
    <div class="mask" v-show="showLoading">
      <Loading />
    </div>
    <div class="table-wrapper">
      <transition name="fadeIn">
        <table v-if='(dataArray.length != 0)'>
          <thead>
            <tr>
              <td v-for="header in headerArray" :key="header.key">{{ header.label }}</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="data in dataArray" :key="data.id">
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
import NoDataMessage from '@/components/table/NoDataMessage.vue'
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

    }
  }
};
</script>

<style scoped lang="scss">
@import "@/style/variables.scss";

#plain-table {
  position: relative;
  width: 100%;
}

.mask {
  z-index: 10000;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  // background-color: rgba(187, 187, 187, 0.207);
  backdrop-filter: blur(1px);

  .loading {
    margin-top: 10px;
  }
}

.table-wrapper {
  width: 100%;
  overflow: auto;
  padding-bottom: 50px;
  max-height: 45vh;
  text-align: center;
}

table {
  border-collapse: collapse;
  white-space: nowrap;
}

table td {
  padding: 10px 30px;
  font-size: 13px;
}

table thead tr {
  background-color: $subThemeColor;
  color: whitesmoke;
  font-weight: 500;
}

thead td:first-child {
  border-top-left-radius: 1em;
}

thead td:last-child {
  border-top-right-radius: 1em;
}

table tbody tr:nth-child(2n+1) {
  background-color: white;
}

table tbody tr:nth-child(2n) {
  background-color: #eee;
}
</style>