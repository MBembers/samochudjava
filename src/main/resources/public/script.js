let submitBtn = document.getElementById("submit2")

submitBtn.addEventListener("click", async () => await addCar())

async function addCar(){
    const model = document.getElementById("modelInput").value
    const airbags = [
        {
            name: "dairbag",
            value: document.getElementById("dairbag").value
        },
        {
            name: "pairbag",
            value: document.getElementById("pairbag").value
        },
        {
            name: "bairbag",
            value: document.getElementById("bairbag").value
        },
        {
            name: "sairbag",
            value: document.getElementById("sairbag").value
        }
    ]
    const year = document.getElementById("year").value
    const color = document.getElementById("color").value

    const response = await fetch("add", {
        method: "post",
        body: JSON.stringify({
            model,
            airbags,
            year,
            color
        })
    })

    const data = await response.json()
    alert(JSON.stringify(data, null, 3))
}
