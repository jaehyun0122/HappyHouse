import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    info: null,
  },
  getters: {
    checkInfo: function (state) {
      return state.info;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_INFO: (state, info) => {
      state.isLogin = true;
      state.info = info;
    },
  },
  actions: {
    async memberConfirm({ commit }, member) {
      await login(
        member,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.nickname,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_INFO", response.data.info);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
