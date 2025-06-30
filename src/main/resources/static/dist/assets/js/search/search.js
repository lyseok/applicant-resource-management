document.addEventListener("DOMContentLoaded", () => {
  const schBox      = document.querySelector(".main_option");
  const schTab      = schBox.children[0];
  const schItems    = schTab.querySelectorAll("li");
  const schContents = schBox.querySelectorAll(".wrap_section_contents > div");
  

  schItems.forEach(item => {
    item.addEventListener("click", () => {
      const isActive = item.classList.contains("on");

      // 1) 일단 모두 비우고
      schItems.forEach(li => li.classList.remove("on"));
      schContents.forEach(div => div.classList.remove("on"));

      if (!isActive) {
        // 2) 기존에 활성화되지 않았으면만 켜기
        item.classList.add("on");
        const key = Array.from(item.classList).find(c => c !== "on");
        const target = schBox.querySelector(
          `.wrap_section_contents > .${key}`
        );
        if (target) target.classList.add("on");
        if (item.classList.contains('keyword_section') && item.classList.contains('on')) item.style.zIndex = 0;
      }
    });
  });
  
});

document.addEventListener("DOMContentLoaded", () => {
  const containers = document.querySelectorAll(".default_option .search_option");

  containers.forEach(container => {
    const btn 		= container.querySelector(".btn_open_layer");
    const btnClose  = container.querySelector(".btn_close");
    btn.addEventListener("click", () => {
      // 1) 다른 열려 있는 것 모두 닫기
      containers.forEach(c => c.classList.remove("open"));

      // 2) 내 컨테이너만 토글
      container.classList.toggle("open");
      
    });
  	btnClose.addEventListener("click", () => {
	  container.classList.remove("open");
  	})
  });
});


document.addEventListener("DOMContentLoaded", function(){
	const customSelect = document.querySelector(".custom-select");
    const selectStyled = customSelect.querySelector(".select-styled");
    const selectOptions = customSelect.querySelectorAll(".select-options li");
    console.log(customSelect);

    selectStyled.addEventListener("click", function () {
      customSelect.classList.toggle("open");
    });

    selectOptions.forEach(option => {
      option.addEventListener("click", function () {
        const value = option.getAttribute("data-value");
        selectStyled.querySelector("span").textContent = option.textContent;
        customSelect.classList.remove("open");

        console.log(value);
      });
    });
})