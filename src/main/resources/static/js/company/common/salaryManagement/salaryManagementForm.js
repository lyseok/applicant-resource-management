/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
  axios.get('/ajax/code/cmncodegroup/RANK')
       .then(resp =>{
          console.log(resp.data.cmnCodeList); 
          const rankList = resp.data.cmnCodeList;
         
          
       })

});
