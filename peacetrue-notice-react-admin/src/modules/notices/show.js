import React from 'react';
import {DateField, ReferenceField, RichTextField, Show, SimpleShowLayout, TextField} from 'react-admin';
import PublishTimeField from "./PublishTimeField";

export const NoticeShow = (props) => {
    console.info('NoticeShow:', props);
    return (
        <Show {...props}>
            <SimpleShowLayout>
                <TextField source="title"/>
                <RichTextField source="content"/>
                <ReferenceField source="stateId" reference="enums/noticeState" link={false}>
                    <TextField source="name"/>
                </ReferenceField>
                <PublishTimeField/>
                <TextField source="viewCount"/>
                <TextField source="remark"/>
                <ReferenceField reference="users" source="creatorId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
                <ReferenceField reference="users" source="modifierId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="modifiedTime" showTime/>
            </SimpleShowLayout>
        </Show>
    );
};
