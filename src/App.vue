<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>
@font-face {
  font-family: "icomoon";
  src: url("assets/fonts/icomoon.eot?1egmr0");
  src: url("assets/fonts/icomoon.eot?1egmr0#iefix") format("embedded-opentype"),
    url("assets/fonts/icomoon.ttf?1egmr0") format("truetype"),
    url("assets/fonts/icomoon.woff?1egmr0") format("woff"),
    url("assets/fonts/icomoon.svg?1egmr0#icomoon") format("svg");
  font-weight: normal;
  font-style: normal;
  font-display: block;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
a {
  text-decoration: none;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
input:focus {
  outline: none;
}
</style>
