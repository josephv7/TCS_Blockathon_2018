/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* global getAssetRegistry getFactory emit */

/**
 * Sample transaction processor function.
 * @param {org.example.basic.CreateRecord} tx The sample transaction instance.
 * @transaction
 */
async function sampleTransaction(tx) {  // eslint-disable-line no-unused-vars

    // Save the old value of the asset.
    const oldValue = tx.asset.value;

    // Update the asset with the new value.
    tx.asset.value = tx.newValue;

    // Get the asset registry for the asset.
    const assetRegistry = await getAssetRegistry('org.example.basic.MedicalRecord');
    // Update the asset in the asset registry.
    await assetRegistry.update(tx.asset);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent('org.example.basic', 'SampleEvent');
    event.asset = tx.asset;
    event.oldValue = oldValue;
    event.newValue = tx.newValue;
    emit(event);
}


/**
 * Sample transaction processor function.
 * @param {org.example.basic.ShareDoctor} sh The sample transaction instance.
 * @transaction
 */
async function sharing(sh) {  // eslint-disable-line no-unused-vars

    // Save the old value of the asset.
    const oldDoctorId = sh.asset.doctorId;

    // Update the asset with the new value.
    sh.asset.doctorId = sh.newDoctorId;

    // Get the asset registry for the asset.
    const assetRegistry = await getAssetRegistry('org.example.basic.MedicalRecord');
    // Update the asset in the asset registry.
    await assetRegistry.update(sh.asset);

    // Emit an event for the modified asset.
    let event = getFactory().newEvent('org.example.basic', 'SharingEvent');
    event.asset = sh.asset;
    event.oldDoctorId = oldDoctorId;
    event.newDoctorId = sh.newDoctorId;
    emit(event);
}




