<template>
  <div class="loading" v-if="showLoading"></div>
</template>

<script>
export default {
  props: {
    showLoading: {
      type: Boolean,
      required: true,
      default: false,
    },
  },
};
</script>

<style scoped>
.loading {
  display: flex;
  justify-content: center;
}

.loading::after {
  content: "";
  width: 30px;
  height: 30px;
  border: 3px solid transparent;
  border-top-color: #aec4a3;
  border-radius: 50%;
  animation: loading 1s ease infinite;
}

@keyframes loading {
  to {
    transform: rotate(1turn);
  }
}
</style>