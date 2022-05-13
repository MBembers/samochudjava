amogus();



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
                // console.log(key, value)
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
                    data ? amogus() : alert("Error occurred during deletion");
                })
            })

            td.appendChild(button)
            tr.appendChild(td)

            td = document.createElement("td")
            button = document.createElement("button")
            button.innerText = "UPDATE"
            button.addEventListener("click", async ()=>{
                await updateModal(dataRow)
            })

            td.appendChild(button)
            tr.appendChild(td)

            table.appendChild(tr)
        }
}

async function updateModal(car){
    let root = document.documentElement;
    root.style.setProperty('--overlay-display', "flex");
    let btnConfirm = document.getElementById("modal-confirm")
    let btnCancel = document.getElementById("modal-cancel");
    let modelInp = document.getElementById('model-input');
    modelInp.value = car.model;
    let yearSelect = document.getElementById('year-select');
    for(let i = 1998; i < 2020; i++){
        let option = document.createElement("option");
        option.text = i.toString();
        option.value = i.toString();
        yearSelect.appendChild(option);
        if(i == car.year) option.selected = true
    }

    btnConfirm.addEventListener('click', ()=>{
        car.model = modelInp.value
        car.year = yearSelect.value;
        // console.log("to send: ",car)

        fetch("update", {
            method: "post",
            body: JSON.stringify(car)
        }).then(response => response.json()).then(data => {
            data ? (()=>{console.log("response: ", data); amogus(); })() : alert("Error occurred during update");
        })
    })

    btnCancel.addEventListener('click', ()=>{
        root.style.setProperty('--overlay-display', "none")
    })

    // TU SKONCZYLES OGARNIJ BUTTONY XDDD

}