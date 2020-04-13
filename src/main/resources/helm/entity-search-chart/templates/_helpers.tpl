{{/* vim: set filetype=mustache: */}}
{{/*
Expand the field of the chart.
*/}}
{{- define "customer-search-chart.field" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app field.
We truncate at 63 chars because some Kubernetes field fields are limited to this (by the DNS naming spec).
If release field contains chart field it will be used as a full field.
*/}}
{{- define "customer-search-chart.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $field := default .Chart.Name .Values.nameOverride -}}
{{- if contains $field .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $field | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart field and version as used by the chart label.
*/}}
{{- define "customer-search-chart.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}
