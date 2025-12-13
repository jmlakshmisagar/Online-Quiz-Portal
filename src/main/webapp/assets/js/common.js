(function(){
  const $ = (s, r=document) => r.querySelector(s);
  const $$ = (s, r=document) => [...r.querySelectorAll(s)];

  window.$u = {
    $,
    $$,
    disable: el => el && el.setAttribute("disabled","true"),
    enable: el => el && el.removeAttribute("disabled"),
    storeGet: k => JSON.parse(localStorage.getItem(k)),
    storeSet: (k,v) => localStorage.setItem(k,JSON.stringify(v)),
    storeDel: k => localStorage.removeItem(k)
  };
})();
