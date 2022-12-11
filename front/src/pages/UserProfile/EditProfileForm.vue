<template>
  <card>
    <h4 slot="header" class="card-title" v-if="isUserid">정보 보기 및 수정</h4>
    <h4 slot="header" class="card-title" v-else>회원 가입</h4>
    <b-form @submit="onSubmit">
      <div class="row">
        <div class="col-md-4">
          <base-input type="text"
                    id="nickname"
                    label="ID"
                    :disabled="isUserid"
                    placeholder="ID"
                    v-model="member.nickname">
          </base-input>
        </div>
        <div class="col-md-3">
          <base-input type="text"
                    id="name"
                    label="이름"
                    placeholder="Username"
                    v-model="member.name">
          </base-input>
        </div>
        <div class="col-md-5">
          <base-input type="email"
                    id="email"
                    label="Email"
                    placeholder="Email"
                    v-model="member.email">
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <base-input type="password"
                    id="password"
                    label="비밀번호"
                    placeholder="password"
                    v-model="member.password">
          </base-input>
        </div>
        <div class="col-md-6">
          <base-input type="password"
                    id="passwordcheck"
                    label="비밀번호 확인"
                    placeholder="password check"
                    v-model="member.passwordcheck">
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="form-group" v-if="isUserid">
            <label>About Me</label>
            <textarea rows="5" class="form-control border-input"
                      placeholder="Here can be your description"
                      v-model="member.about">
              </textarea>
          </div>
        </div>
      </div>
      <div class="text-center">
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
        <b-button variant="danger" v-if="this.type === 'modify'" @click="deleteMember">회원 탈퇴</b-button>
      </div>
      <div class="clearfix"></div>
    </b-form>
  </card>
</template>
<script>
import { findById, signup, modifyMember } from "@/api/member";
import { mapState, mapMutations } from "vuex";
import { deleteMember } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberWriteForm",
  data() {
    return {
      member: {
        nickname: "",
        name: "",
        password: "",
        passwordcheck: "",
        email: "",
        about: "안녕하세요, 잘 부탁 드립니다.",
      },
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["info"]),
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
          this.member.email = data.info.email;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_INFO"]),
    deleteMember() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteMember(this.info.nickname, () => {
          this.SET_IS_LOGIN(false);
          this.SET_INFO(null);
          sessionStorage.removeItem("access-token");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        });
      }
    },
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
        !this.member.email &&
        ((msg = "이메일을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registMember() : this.updateMember();
    },
    registMember() {
      signup(
        {
          nickname: this.member.nickname,
          name: this.member.name,
          password: this.member.password,
          email: this.member.email,
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
          email: this.member.email,
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
<style>

</style>
