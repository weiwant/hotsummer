<template>
  <div id="table-wrapper">
    <table>
      <thead>
        <tr>
          <td v-for="item in header" :key="item.value">{{ item.key }}</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="obj in data" :key="obj.courseNumber">
          <td v-for="count in header.length">
            {{ obj[header[count - 1].value] }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  props: {
    header: {
      type: Array,
      required: true,
    },
    data: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>
#table-wrapper {
  width: 100%;
  overflow: auto;
  padding-bottom: 50px;
  max-height: 45vh;
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
  background-color: #677f84;
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