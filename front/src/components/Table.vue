<template>
  <table class="table">
    <thead>
      <slot name="columns">
        <tr>
          <th v-for="column in columns" :key="column">{{column}}</th>
        </tr>
      </slot>
    </thead>
    <tbody>
    <tr v-for="(item, index) in data" :key="index">
      <slot :row="item">
        <td v-for="column in columns" :key="column" v-if="hasValue(item, column)">
          <router-link
          style="color:black"
          :to="{ name: 'BoardView', params: { boardId: item.boardId } }">
            {{itemValue(item, column)}}
          </router-link>
        </td>
      </slot>
    </tr>
    </tbody>
  </table>
</template>
<script>
  export default {
    name: 'l-table',
    props: {
      columns: Array,
      data: Array,
      boardId: Number,
      writer: String,
      title: String,
      hit: Number,
      regtime: String,
    },
    methods: {
      hasValue (item, column) {
        return item[column.toLowerCase()] !== 'undefined'
      },
      itemValue (item, column) {
        return item[column.toLowerCase()]
      }
    },
    created() {
      console.log(this.title);
      console.log(this.regtime);
      console.log(this.boardId);
    },
  }
</script>
<style>
</style>
