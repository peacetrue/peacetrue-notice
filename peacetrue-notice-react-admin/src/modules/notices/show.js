import React from 'react';
import {DateField, Show, SimpleShowLayout, TextField, NumberField} from 'react-admin';

export const NoticeShow = (props) => {
    console.info('NoticeShow:', props);
    return (
        <Show {...props}>
            <SimpleShowLayout>
                <NumberField source="sourceId"/>
                <TextField source="title"/>
                <TextField source="content"/>
                <TextField source="stateId"/>
                <DateField source="publishedTime" showTime/>
                <NumberField source="viewCount"/>
                <TextField source="remark"/>
                <NumberField source="creatorId"/>
                <DateField source="createdTime" showTime/>
                <NumberField source="modifierId"/>
                <DateField source="modifiedTime" showTime/>
            </SimpleShowLayout>
        </Show>
    );
};
