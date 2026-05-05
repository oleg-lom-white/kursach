<template>
    <div>
        <h2>Parts (Total: {{parts.length}})</h2>
        <ol>
            <li v-for="part in sortedParts" :key="part.id">
                ------------------------------------<br/>
                creation date: {{part.date}}<br/>
                type: {{part.type}}<br/>
                size: {{part.size}}<br/>
                material: {{part.material}}<br/>
            </li>
        </ol>
        <form @submit.prevent="savePart">
            <div>
                <h3>Materials</h3>
                    <input type="radio" value="steel" v-model="partForm.material">Steel</input>
                    <input type="radio" value="Aluminium" v-model="partForm.material">Aluminium</input>
                    <input type="radio" value="Bronze" v-model="partForm.material">Bronze</input>
            </div>
        <div>
            <h3>Parametrs</h3>
            <label> Type </label>
            <select v-model="partForm.type">
                <option value="вал">Вал</option>
                <option value="Шестерня">Шестерня</option>
                <option value="Втулка">Втулка</option>
            </select>
            <label> Size </label>
            <select v-model="partForm.size">
                <option value="10">10mm</option>
                <option value="12">12mm</option>
                <option value="15">15mm</option>
            </select>
        </div>
        <div>
            <label> Preview </label>
            <textarea v-model="summary" readonly></textarea>
            <button type="button" @click="previewSummary"> Check </button>
            <button type="button" @click="reset"> Reset </button>
        </div>
        <div>
            <button type="button" :disabled="!correct" @click="savePart"> Save </button>
        </div>
        </form>
    </div>
</template>

<script>
import {ref,onMounted,computed} from 'vue'
import axios from 'axios'
export default {
    setup() {
        const parts = ref([])
        const correct = ref(false)
        const summary = ref(``)
        const partForm = ref({material: '', type: '', size: ''})
        const getParts = async () => {
            const response = await axios.get('/api/parts/all')
            parts.value = response.data
        }
        const sortedParts = computed(() => {
            return [...parts.value].sort((a,b)=>new Date(a.date) - new Date(b.date))
        } )
        const previewSummary = () => {
            if (partForm.value.material.trim !== '' &&
                partForm.value.type.trim !== '' &&
                partForm.value.size.trim !== '') {
            correct.value = true
        }
            summary.value = `Material: ${partForm.value.material}\nType: ${partForm.value.type}\n Size: ${partForm.value.size}`
        }
        const reset = () => {
            summary.value = ''
            partForm.value = {material: '', type: '', size: ''}
            correct.value = false
        }
        const savePart = async () => {
            await axios.post ('/api/parts/', partForm.value)
            reset()
            getParts()
        }
        onMounted(getParts)
        return {
        parts,sortedParts,partForm,previewSummary,summary,reset,savePart,correct
        }
    }
}
</script>