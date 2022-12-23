<template>
  <div class="container text-center">
    <h2>Le foto di Pippo</h2>
    <label for="filter">Search a picture by name or tag: </label>
    <input type="text" id="filter" v-model="searchedValue" v-on:keyup.enter="fetchFilteredPics()">
        <div v-for="pic in pics" :key="pic.id" class="card-container d-flex">
          <div class="col-3"></div>
          <div class="col-4">
            <img :src="pic.url" :alt="pic.title" class="img-fluid">
          </div>
          <div class="col-2 p-1 overflow-auto">
            <h3>Title: {{ pic.title }}</h3>
            <p>Description: {{ pic.description }}</p>
            <p class="cloud" v-for="comment in pic.comments" :key="comment.id">
              {{ comment.content }}
            </p>
            <textarea v-on:keyup.enter="postComment(pic)" :id="'content-' + pic.id" name="content" rows="2" placeholder="Leave a comment.."></textarea>
          </div>            
        </div> 
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'MainComponent',
    data(){
        return {
            pics: [],
            searchedValue: "",
        }
    },
    methods: {
    fetchPics() {
      axios.get("http://localhost:8080/api/1/pics")
        .then(response => {
          this.pics = response.data;

          this.pics.forEach(pic => {
              
               axios.get("http:localhost:8080/api/1/comments/" + pic.id)
                 .then(response => {
                   const comments = [];
                   comments.push(response.data);
                   console.log(comments)
               })
               .catch(error => {
                 console.log(error);
               });
              
              axios.get("http://localhost:8080/api/1/categories/pic/" + pic.id)
              .then(response => {
                const categories = [];
                categories.push(response.data);
                console.log(categories)
              })
              .catch(error => {
                console.log(error);
              });

          })
        })
        .catch(error => {
          console.log(error);
        });
    },
    postComment(pic){
      const picId = pic.id
      const textareaId = "content-" + picId
      const content = document.getElementById(textareaId).value;
      console.log(String(content))
      const newComment = {
        content: String(content),
        id: picId
      }
      axios.post("http://localhost:8080/api/1/comments/add/" + picId , newComment)
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error);
        });
    },
    fetchFilteredPics(){
      if(this.searchValue === '') return this.getPhotos();
      axios.get("http://localhost:8080/api/1/pics/search/" + this.searchedValue)
        .then(response => {
          this.pics = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
    mounted(){
        this.fetchPics();
    }
}
</script>

<style scoped lang="scss">
@import '../assets/sass/style.scss';
 
</style>