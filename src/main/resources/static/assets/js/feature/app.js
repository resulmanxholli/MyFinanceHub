class FeatureApp {
    constructor() {
        this.api = new FeatureApi();
        this.init();
    }

    async init() {
        this.listCard = document.getElementById("listCard");
        this.formCard = document.getElementById("formCard");
        this.viewCard = document.getElementById("viewCard");
        this.featureIdInput = document.getElementById("featureId");
        this.featureNameInput = document.getElementById("featureName");
        this.featureTableBody = document.getElementById("featureTableBody");

        this.viewIdDD = document.getElementById("viewId");
        this.viewNameDD = document.getElementById("viewName");
        this.viewCreatedDD = document.getElementById("viewCreated");
        // Swal.fire({
        //     position: "top-end",
        //     icon: "success",
        //     title: "Your work has been saved",
        //     showConfirmButton: false,
        //     timer: 1500
        // });

        await this.loadFeatures();


        await this.bindEvents();

    }

    async loadFeatures() {
        this.api.findAll().then(features => {
            console.table(features);
            this.showTable(features);
        }).catch((err) => {
            Swal.fire({
                title: 'Error!', text: 'Do you want to continue: ' + err, icon: 'error', confirmButtonText: 'Cool'
            })
        });
    }

    async bindEvents() {
        document.getElementById("btnShowCreate").addEventListener('click', () => this.showForm("form"));
        document.getElementById("btnCancelForm").addEventListener('click', () => this.showForm("list"));
        document.getElementById("btnBackFromView").addEventListener('click', () => this.showForm("list"));
        document.getElementById("btnRefresh").addEventListener("click",async (event)=>await this.loadFeatures());
        document.getElementById("btnSave").addEventListener('click', async (event) => {
            event.preventDefault();
            await this.save();
        });
    }

    showTable(features) {

        if (!features || features.length === 0) {
            this.featureTableBody.innerHTML = `<tr><td colspan="4">No data</td></tr>`;
        } else {
            this.featureTableBody.innerHTML = features.map((feature, idx) => `
                <tr>
                    <td>${feature.id}</td>
                    <td>${feature.name}</td>
                    <td>${feature.createdAt ? new Date(feature.createdAt).toLocaleString() : ''}</td>
                    <td class="text-end">
                        <button class="btn btn-outline-secondary" data-id="${feature.id}" data-action="view"><i class="bi bi-eye"></i></button>
                        <button class="btn btn-outline-primary" data-id="${feature.id}" data-action="edit"><i class="bi bi-pencil"></i></button>
                        <button class="btn btn-outline-danger" data-id="${feature.id}" data-action="delete"><i class="bi bi-trash"></i></button>
                    </td>
                </tr>
                    `).join('');

            this.featureTableBody.querySelectorAll("button[data-action]").forEach(button => {
                button.addEventListener('click', (event) => {
                    event.preventDefault();
                    this.handleDataAction(event);
                })
            })

        }
    }


    async handleDataAction(event) {
        const id = event.currentTarget.dataset.id; //event.currentTarget.getAttribute('data-id')
        const action = event.currentTarget.dataset.action;
        if (!id) return;
        switch (action) {
            case 'view':
                await this.showFeatureView(id);
                break;
            case 'edit':
                await this.showEditFor(id);
                break;
            case 'delete':
                await this.confirmDelete(id);
                break;
        }
    }

    async showFeatureView(featureId) {
        const feature = await this.api.findOne(featureId);
        if (!feature) {
            alert("cannot load feature for id:" + featureId);
            return;
        }

        this.viewIdDD.textContent = feature.id;
        this.viewNameDD.textContent = feature.name;
        this.viewCreatedDD.textContent = feature.createdAt ? new Date(feature.createdAt).toLocaleString() : '';
        this.showForm('view');

    }

    async showEditFor(featureId) {
        const feature = await this.api.findOne(featureId);
        if (!feature) {
            alert("cannot load feature for id:" + featureId);
            return;
        }

        this.featureIdInput.value = feature.id;
        this.featureNameInput.value = feature.name;
        this.showForm("form");
    }

    async confirmDelete(featureId) {


        const result = await Swal.fire({
            title:`Delete feature: ${featureId}?`,
            text:'This action cannot be undone! you delete that foreever',
            icon:'warning',
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        })

        if(!result.isConfirmed) return;
        else {
            await this.api.delete(featureId);
            await this.loadFeatures();
        }


    }

    showForm(mode) {
        const showForm = mode === "form";
        const showList = mode === "list";
        const showView = mode === "view";
        this.formCard.classList.toggle('d-none', !showForm);
        this.viewCard.classList.toggle('d-none', !showView);
        this.listCard.classList.toggle('d-none', !showList);
    }

    async save() {
        const featureId = this.featureIdInput.value;
        const name = this.featureNameInput.value;
        const isNew = featureId == "" && Number(featureId) == 0;
        if (isNew) {
            //thirre API per me regjistru
            console.log("Adding...")
            await this.api.post({name: name});
        } else {
            //thirre API per me bo update
            console.log("Updating...")
            await this.api.put(featureId, {name: name});
        }

        this.showForm("list")
        await this.loadFeatures();
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const app = new FeatureApp();
})