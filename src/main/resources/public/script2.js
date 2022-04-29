async function amogus(){
        const table = document.getElementById("table");
        const response = await fetch("json", {
            method: "post"
        });
        const data = await response.json();

        for(let dataRow of data){
            const tr = document.createElement("tr")
            for(let [key, value] of Object.entries(dataRow)){
                console.log(key, value)
            }
        }
}
amogus();