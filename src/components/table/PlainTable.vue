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

<style>
</style>