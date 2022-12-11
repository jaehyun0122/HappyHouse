<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card class="strpied-tabled-with-hover"
                body-classes="table-full-width table-responsive"
          >
            <template slot="header">
              <h4 class="card-title">게시판</h4>
              <p class="card-category">Board</p>
              <b-col class="text-right">
                <b-button variant="outline-primary" @click="moveWrite()">
                  글쓰기
                </b-button>
              </b-col>
            </template>
            <l-table class="table-hover table-striped"
                     :columns="columns"
                     :data="articles">
            </l-table>
          </card>
          <!-- 페이지 네이션 부분 -->
          <!-- @change변화가 있다면 pageChange함수(다음 페이지 게시글불러오기) -->
          <!-- v-model 현재 페이지를 전달 -->
          <!-- total-rows : 총 게시글의 개수 -->
          <!-- per-page : 한 페이지당 보여줄 게시글 -->
          <b-pagination
            @change="pageChange"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            align="center">
          </b-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BoardListRow from "@/components/board/child/BoardListRow";
import { listArticle, totalArticle } from "@/api/board.js";
import LTable from 'src/components/Table.vue'
import Card from 'src/components/Cards/Card.vue'
const tableColumns = ['Title', 'Hit', 'Writer', 'RegTime']
export default {
  name: "BoardList",
  components: {
    BoardListRow,
    LTable,
    Card
  },
  data() {
    return {
      columns: [...tableColumns],
      articles: [], // 현재 페이지 게시글 저장 배열
      perPage: 10, // 페이지당 보여줄 게시글 개수
      pg: 1, // 초기 페이지
      currentPage: 1, // 현재 페이지
      total: 0, // 총 페이지 개수 초기값 0. totalArticle함수 실행시 총 게시글 개수 리턴
    };
  },
  created() {
    // param에 게시글 불러오기 위한 요청 값 설정
    let param = {
      pg: 1, // 초기 페이지
      spp: this.perPage, // 요청시 페이지에 따른 게시글 개수
      start: 1, // 게시글 시작 번호
    };
    // 총 게시글 개수를 반환
    totalArticle(
      (response) => {
        this.total = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    // 위에 설정한 param값에 따른 게시글 불러오기
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log(this.articles);
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
    // 페이지네이션의 다음페이지 or 이전 페이지누를 때 실행
    pageChange(value) {
      this.currentPage = value;
      console.log("페이지 번호: " + value);
      let param = {
        pg: value,
        start: value == 1 ? 1 : value + 3,
        spp: this.perPage,
      };

      listArticle(
        param,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  // computed에 rows함수를 이용해 게시글 추가, 삭제시 총 게시글 가져오기위해
  computed: {
    rows() {
      console.log("게시글 총 개수: " + this.total);
      return this.total;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
