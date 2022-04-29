async function amogus(){
        const table = document.getElementById("table");
        table.innerHTML = ""
        const response = await fetch("json", {
            method: "post"
        });
        const data = await response.json();

        for(let dataRow of data){
            const tr = document.createElement("tr")
            for(let [key, value] of Object.entries(dataRow)){
                console.log(key, value)
                const td = document.createElement("td");
                if(key == "airbags"){
                    text = `kierowca:${value[0].value}
                            pasazer:${value[1].value}
                            tylnie:${value[2].value}
                            boczne:${value[3].value}`
                }
                else if(key == "color"){
                    text = ""
                    td.style.backgroundColor = value
                    td.style.width = '80px'
                }
                else
                    text = value
                td.innerText = text
                tr.appendChild(td)
            }
            let td = document.createElement("td")
            let button = document.createElement("button")

            button.innerText = "DELETE"
            button.addEventListener("click", ()=>{
                fetch("delete", {
                    method: "post",
                    body: JSON.stringify(dataRow)
                }).then(response => response.json()).then(data => {
                    data ? amogus() : alert("blad podczas usuwania");
                })
            })

            button.innerText = "UPDATE"
            button.addEventListener("click", ()=>{
                fetch("update", {
                    method: "post",
                    body: JSON.stringify(dataRow)
                }).then(response => response.json()).then(data => {
                    data ? amogus() : alert("blad podczas usuwania");
                })
            })

            td.appendChild(button)
            tr.appendChild(td)

            table.appendChild(tr)
        }
}
amogus();
