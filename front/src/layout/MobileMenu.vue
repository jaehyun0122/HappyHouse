<template>
  <ul class="nav nav-mobile-menu" v-if="info">
    <li class="nav-item">
      <router-link :to="{ name: 'User', params: { nickname: this.info.nickname } }" class="nav-link">My Profile</router-link>
    </li>

    <li class="nav-item">
      <a class="nav-link" @click.prevent="onClickLogout">
        <span class="d-lg-none">Log out</span>
      </a>
    </li>
  </ul>
  <ul class="nav nav-mobile-menu" v-else>
    <li class="nav-item">
      <router-link :to="{ name: 'SignIn' }" class="nav-link"> Sign In</router-link>
    </li>

    <li class="nav-item">
      <router-link :to="{ name: 'SignUp'}" class="nav-link"> Sign up</router-link>
    </li>
  </ul>
</template>
<script>
import { mapState, mapMutations } from "vuex";

  const memberStore = "memberStore";
  export default {
    name: 'mobile-menu',
    computed: {
      ...mapState(memberStore, ["isLogin", "info"]),
    },
    methods: {
      ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_INFO"]),
      onClickLogout() {
        this.SET_IS_LOGIN(false);
        this.SET_INFO(null);
        sessionStorage.removeItem("access-token");
        if (this.$route.path != "/") this.$router.push({ name: "Home" });
      },
    }
  }
</script>
<style>
</style>
