<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="nickname-group"
          label="ID:"
          label-for="nickname"
          description="아이디를 입력하세요."
        >
          <b-form-input
            id="nickname"
            :disabled="isUserid"
            v-model="member.nickname"
            type="text"
            required
            placeholder="아이디 입력"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="name-group"
          label="이름:"
          label-for="name"
          description="이름을 입력하세요."
        >
          <b-form-input
            id="name"
            v-model="member.name"
            type="text"
            required
            placeholder="이름 입력"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="password-group"
          label="PW:"
          label-for="password"
          description="비밀번호를 입력하세요."
        >
          <b-form-input
            id="password"
            v-model="member.password"
            type="password"
            required
            placeholder="비밀번호 입력"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="pwCheck-group"
          label="PW 확인:"
          label-for="passwordcheck"
          description="비밀번호를 재입력하세요."
        >
          <b-form-input
            id="passwordcheck"
            v-model="member.passwordcheck"
            type="password"
            required
            placeholder="비밀번호 재입력"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="email-group"
          label="E-mail:"
          label-for="email"
          description="이메일을 입력하세요."
        >
          <b-form-input
            id="email"
            v-model="member.email1"
            type="text"
            required
            placeholder="이메일 입력"
          ></b-form-input>
          @
          <select v-model="member.email2">
            <option value="@ssafy.com">싸피</option>
            <option value="@naver.com">네이버</option>
            <option value="@kakao.com">카카오</option>
            <option value="@google.com">구글</option>
          </select>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >회원 가입</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >회원 정보 수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { findById, signup, modifyMember } from "@/api/member";

export default {
  name: "MemberWriteForm",
  data() {
    return {
      member: {
        nickname: "",
        name: "",
        password: "",
        passwordcheck: "",
        email1: "",
        email2: "",
        email: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      findById(
        this.$route.params.nickname,
        ({ data }) => {
          this.member.nickname = data.info.nickname;
          this.member.name = data.info.name;
          let emails = data.info.email.split("@");
          this.member.email1 = emails[0];
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.member.nickname && ((msg = "ID를 입력해주세요"), (err = false));
      err &&
        !this.member.name &&
        ((msg = "이름을 입력해주세요"), (err = false));
      err &&
        !this.member.password &&
        ((msg = "비밀번호를 입력해주세요"), (err = false));
      err &&
        !(this.member.password === this.member.passwordcheck) &&
        ((msg = "비밀번호를 확인해주세요"), (err = false));
      err &&
        !this.member.email1 &&
        ((msg = "이메일을 입력해주세요"), (err = false));
      err &&
        !this.member.email2 &&
        ((msg = "이메일 도메인을 선택해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registMember() : this.updateMember();
    },
    onReset(event) {
      event.preventDefault();
      this.member.nickname = "";
      this.member.name = "";
      this.member.password = "";
      this.member.passwordcheck = "";
      this.member.email1 = "";
      this.member.email2 = "";
    },
    registMember() {
      signup(
        {
          nickname: this.member.nickname,
          name: this.member.name,
          password: this.member.password,
          email: this.member.email1 + this.member.email2,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveHome();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateMember() {
      modifyMember(
        this.member.nickname,
        {
          nickname: this.member.nickname,
          name: this.member.name,
          password: this.member.password,
          email: this.member.email1 + this.member.email2,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "Home" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
