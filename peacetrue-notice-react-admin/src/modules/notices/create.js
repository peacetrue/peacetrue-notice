import React from 'react';
import {Create, required, SimpleForm, DateTimeInput, TextInput, NumberInput, minValue, maxLength} from 'react-admin';

export const NoticeCreate = (props) => {
    console.info('NoticeCreate:', props);
    return (
        <Create {...props}>
            <SimpleForm>
                <NumberInput source="sourceId" validate={[required(), minValue(0)]} min={0}/>
                <TextInput source="title" validate={[required(), maxLength(255)]}/>
                <TextInput source="content" validate={[required(), maxLength(1024)]}/>
                <TextInput source="stateId" validate={[required(), maxLength(3)]}/>
                <DateTimeInput source="publishedTime" showTime validate={[required(),]}/>
                <NumberInput source="viewCount" validate={[required(), minValue(0)]} min={0}/>
                <TextInput source="remark" validate={[required(), maxLength(255)]}/>
            </SimpleForm>
        </Create>
    );
};
