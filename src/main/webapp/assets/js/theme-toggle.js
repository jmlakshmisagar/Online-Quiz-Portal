(function(){
  const root = document.documentElement;
  const btn = document.querySelector("[data-theme-toggle]");
  const KEY = "theme";

  function set(t){
    if(t === "light") root.setAttribute("data-theme","light");
    else root.removeAttribute("data-theme");
    localStorage.setItem(KEY,t);
    if(btn) btn.textContent = t === "light" ? "Light" : "Dark";
  }

  const saved = localStorage.getItem(KEY);
  set(saved || "dark");

  if(btn){
    btn.onclick = () => set(
      root.getAttribute("data-theme") === "light" ? "dark" : "light"
    );
  }
})();
