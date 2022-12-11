<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="dark" show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.boardId}.
          ${article.title} [${article.hit}]</h3><div><h6>${article.writer}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <comment-view />
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";
import CommentView from "@/components/board/child/CommentView.vue";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardView",
  components: {
    CommentView,
  },
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["info"]),
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    getArticle(
      this.$route.params.boardId,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_INFO"]),
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      console.log(this.article.writer);
      console.log(this.info.nickname);
      if (this.article.writer === this.info.nickname) {
        this.$router.replace({
          name: "BoardUpdate",
          params: { boardId: this.article.boardId },
        });
        //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
      } else {
        alert("권한이 없습니다.");
      }
    },
    removeArticle() {
      console.log(this.article.writer);
      console.log(this.info.nickname);
      if (this.article.writer === this.info.nickname) {
        if (confirm("정말로 삭제하시겠습니까?")) {
          deleteArticle(this.article.boardId, () => {
            this.$router.push({ name: "BoardList" });
          });
        }
      } else {
        alert("권한이 없습니다.");
      }
    },
  },
};
</script>

<style></style>
