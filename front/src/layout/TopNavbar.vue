<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <router-link class="navbar-brand" :to="{ name: 'Home' }">
        <i class="nc-icon nc-bank"></i>
        Happy House</router-link>
      <button type="button"
              class="navbar-toggler navbar-toggler-right"
              :class="{toggled: $sidebar.showSidebar}"
              aria-controls="navigation-index"
              aria-expanded="false"
              aria-label="Toggle navigation"
              @click="toggleSidebar">
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
        <span class="navbar-toggler-bar burger-lines"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="nav navbar-nav mr-auto">
          <li class="nav-item">
            <router-link :to="{ name: 'House'}" class="dropdown-item">
              <i class="nc-icon nc-square-pin">
                아파트 거래 정보
              </i>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'BoardList'}" class="dropdown-item">
              <i class="nc-icon nc-notes">
                게시판
              </i>
            </router-link>
          </li>
        </ul>

        <ul class="navbar-nav ml-auto">
          <base-dropdown v-bind:title="info.name" v-if="info">
            <router-link :to="{ name: 'User', params: { nickname: this.info.nickname } }" class="dropdown-item">내정보보기</router-link>
            <div class="divider"></div>
            <a class="dropdown-item" @click.prevent="onClickLogout">로그아웃</a>
          </base-dropdown>
          <base-dropdown title="로그인" v-else>
            <router-link :to="{ name: 'SignIn' }" class="dropdown-item"> 로그인</router-link>
            <div class="divider"></div>
            <router-link :to="{ name: 'SignUp'}" class="dropdown-item"> 회원가입</router-link>
          </base-dropdown>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  import { mapState, mapMutations } from "vuex";

  const memberStore = "memberStore";
  export default {
    computed: {
      routeName () {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      },
      ...mapState(memberStore, ["isLogin", "info"]),
    },
    data () {
      return {
        activeNotifications: false
      }
    },
    methods: {
      capitalizeFirstLetter (string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown () {
        this.activeNotifications = !this.activeNotifications
      },
      closeDropDown () {
        this.activeNotifications = false,
        console.log(info);
      },
      toggleSidebar () {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      hideSidebar () {
        this.$sidebar.displaySidebar(false)
      },
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
