/**
 * perdoren librarine axios
 */
class FeatureApi {
    constructor() {
        this.url = '/api/features';
    }

    findAll() {
        return fetch(this.url).then(response => response.json());
    }

    findOne(id) {
        return fetch(`${this.url}/${id}`).then(response => response.json());
    }

    post(feature) {
        return fetch(this.url,
            {
                body: JSON.stringify(feature),
                method: 'POST',
                headers: {'Content-Type': 'application/json'}
            }).then(response => response.json());
    }

    put(id, feature) {
        return fetch(`${this.url}/${id}`,
            {
                body: JSON.stringify(feature),
                method: 'PUT',
                headers: {'Content-Type': 'application/json'}
            }).then(response => response.json());
    }

    delete(id) {
        return fetch(`${this.url}/${id}`, {method: 'DELETE'})
            .then(response => response.text());
    }
}