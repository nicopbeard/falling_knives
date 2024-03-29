"""Generated client library for containeranalysis version v1."""
# NOTE: This file is autogenerated and should not be edited by hand.
from apitools.base.py import base_api
from googlecloudsdk.third_party.apis.containeranalysis.v1 import containeranalysis_v1_messages as messages


class ContaineranalysisV1(base_api.BaseApiClient):
  """Generated client library for service containeranalysis version v1."""

  MESSAGES_MODULE = messages
  BASE_URL = u'https://containeranalysis.googleapis.com/'
  MTLS_BASE_URL = u'https://containeranalysis.mtls.googleapis.com/'

  _PACKAGE = u'containeranalysis'
  _SCOPES = [u'https://www.googleapis.com/auth/cloud-platform']
  _VERSION = u'v1'
  _CLIENT_ID = '1042881264118.apps.googleusercontent.com'
  _CLIENT_SECRET = 'x_Tw5K8nnjoRAqULM9PFAC2b'
  _USER_AGENT = u'google-cloud-sdk'
  _CLIENT_CLASS_NAME = u'ContaineranalysisV1'
  _URL_VERSION = u'v1'
  _API_KEY = None

  def __init__(self, url='', credentials=None,
               get_credentials=True, http=None, model=None,
               log_request=False, log_response=False,
               credentials_args=None, default_global_params=None,
               additional_http_headers=None, response_encoding=None):
    """Create a new containeranalysis handle."""
    url = url or self.BASE_URL
    super(ContaineranalysisV1, self).__init__(
        url, credentials=credentials,
        get_credentials=get_credentials, http=http, model=model,
        log_request=log_request, log_response=log_response,
        credentials_args=credentials_args,
        default_global_params=default_global_params,
        additional_http_headers=additional_http_headers,
        response_encoding=response_encoding)
    self.operations = self.OperationsService(self)
    self.projects_notes_occurrences = self.ProjectsNotesOccurrencesService(self)
    self.projects_notes = self.ProjectsNotesService(self)
    self.projects_occurrences = self.ProjectsOccurrencesService(self)
    self.projects = self.ProjectsService(self)

  class OperationsService(base_api.BaseApiService):
    """Service class for the operations resource."""

    _NAME = u'operations'

    def __init__(self, client):
      super(ContaineranalysisV1.OperationsService, self).__init__(client)
      self._upload_configs = {
          }

    def Cancel(self, request, global_params=None):
      r"""Starts asynchronous cancellation on a long-running operation.  The server.
makes a best effort to cancel the operation, but success is not
guaranteed.  If the server doesn't support this method, it returns
`google.rpc.Code.UNIMPLEMENTED`.  Clients can use
Operations.GetOperation or
other methods to check whether the cancellation succeeded or whether the
operation completed despite cancellation. On successful cancellation,
the operation is not deleted; instead, it becomes an operation with
an Operation.error value with a google.rpc.Status.code of 1,
corresponding to `Code.CANCELLED`.

      Args:
        request: (ContaineranalysisOperationsCancelRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Cancel')
      return self._RunMethod(
          config, request, global_params=global_params)

    Cancel.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/operations/{operationsId}:cancel',
        http_method=u'POST',
        method_id=u'containeranalysis.operations.cancel',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}:cancel',
        request_field=u'cancelOperationRequest',
        request_type_name=u'ContaineranalysisOperationsCancelRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes a long-running operation. This method indicates that the client is.
no longer interested in the operation result. It does not cancel the
operation. If the server doesn't support this method, it returns
`google.rpc.Code.UNIMPLEMENTED`.

      Args:
        request: (ContaineranalysisOperationsDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/operations/{operationsId}',
        http_method=u'DELETE',
        method_id=u'containeranalysis.operations.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisOperationsDeleteRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets the latest state of a long-running operation.  Clients can use this.
method to poll the operation result at intervals as recommended by the API
service.

      Args:
        request: (ContaineranalysisOperationsGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Operation) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/operations/{operationsId}',
        http_method=u'GET',
        method_id=u'containeranalysis.operations.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisOperationsGetRequest',
        response_type_name=u'Operation',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists operations that match the specified filter in the request. If the.
server doesn't support this method, it returns `UNIMPLEMENTED`.

NOTE: the `name` binding allows API services to override the binding
to use different resource name schemes, such as `users/*/operations`. To
override the binding, API services can add a binding such as
`"/v1/{name=users/*}/operations"` to their service configuration.
For backwards compatibility, the default name includes the operations
collection id, however overriding users must ensure the name binding
is the parent resource, without the operations collection id.

      Args:
        request: (ContaineranalysisOperationsListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListOperationsResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/operations',
        http_method=u'GET',
        method_id=u'containeranalysis.operations.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisOperationsListRequest',
        response_type_name=u'ListOperationsResponse',
        supports_download=False,
    )

  class ProjectsNotesOccurrencesService(base_api.BaseApiService):
    """Service class for the projects_notes_occurrences resource."""

    _NAME = u'projects_notes_occurrences'

    def __init__(self, client):
      super(ContaineranalysisV1.ProjectsNotesOccurrencesService, self).__init__(client)
      self._upload_configs = {
          }

    def List(self, request, global_params=None):
      r"""Lists occurrences referencing the specified note. Provider projects can use.
this method to get all occurrences across consumer projects referencing the
specified note.

      Args:
        request: (ContaineranalysisProjectsNotesOccurrencesListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListNoteOccurrencesResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}/occurrences',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.notes.occurrences.list',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1/{+name}/occurrences',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsNotesOccurrencesListRequest',
        response_type_name=u'ListNoteOccurrencesResponse',
        supports_download=False,
    )

  class ProjectsNotesService(base_api.BaseApiService):
    """Service class for the projects_notes resource."""

    _NAME = u'projects_notes'

    def __init__(self, client):
      super(ContaineranalysisV1.ProjectsNotesService, self).__init__(client)
      self._upload_configs = {
          }

    def BatchCreate(self, request, global_params=None):
      r"""Creates new notes in batch.

      Args:
        request: (ContaineranalysisProjectsNotesBatchCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (BatchCreateNotesResponse) The response message.
      """
      config = self.GetMethodConfig('BatchCreate')
      return self._RunMethod(
          config, request, global_params=global_params)

    BatchCreate.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes:batchCreate',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.notes.batchCreate',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[],
        relative_path=u'v1/{+parent}/notes:batchCreate',
        request_field=u'batchCreateNotesRequest',
        request_type_name=u'ContaineranalysisProjectsNotesBatchCreateRequest',
        response_type_name=u'BatchCreateNotesResponse',
        supports_download=False,
    )

    def Create(self, request, global_params=None):
      r"""Creates a new note.

      Args:
        request: (ContaineranalysisProjectsNotesCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Note) The response message.
      """
      config = self.GetMethodConfig('Create')
      return self._RunMethod(
          config, request, global_params=global_params)

    Create.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.notes.create',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'noteId'],
        relative_path=u'v1/{+parent}/notes',
        request_field=u'note',
        request_type_name=u'ContaineranalysisProjectsNotesCreateRequest',
        response_type_name=u'Note',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes the specified note.

      Args:
        request: (ContaineranalysisProjectsNotesDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}',
        http_method=u'DELETE',
        method_id=u'containeranalysis.projects.notes.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsNotesDeleteRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets the specified note.

      Args:
        request: (ContaineranalysisProjectsNotesGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Note) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.notes.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsNotesGetRequest',
        response_type_name=u'Note',
        supports_download=False,
    )

    def GetIamPolicy(self, request, global_params=None):
      r"""Gets the access control policy for a note or an occurrence resource.
Requires `containeranalysis.notes.setIamPolicy` or
`containeranalysis.occurrences.setIamPolicy` permission if the resource is
a note or occurrence, respectively.

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsNotesGetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('GetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    GetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}:getIamPolicy',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.notes.getIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:getIamPolicy',
        request_field=u'getIamPolicyRequest',
        request_type_name=u'ContaineranalysisProjectsNotesGetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists notes for the specified project.

      Args:
        request: (ContaineranalysisProjectsNotesListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListNotesResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.notes.list',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1/{+parent}/notes',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsNotesListRequest',
        response_type_name=u'ListNotesResponse',
        supports_download=False,
    )

    def Patch(self, request, global_params=None):
      r"""Updates the specified note.

      Args:
        request: (ContaineranalysisProjectsNotesPatchRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Note) The response message.
      """
      config = self.GetMethodConfig('Patch')
      return self._RunMethod(
          config, request, global_params=global_params)

    Patch.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}',
        http_method=u'PATCH',
        method_id=u'containeranalysis.projects.notes.patch',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'updateMask'],
        relative_path=u'v1/{+name}',
        request_field=u'note',
        request_type_name=u'ContaineranalysisProjectsNotesPatchRequest',
        response_type_name=u'Note',
        supports_download=False,
    )

    def SetIamPolicy(self, request, global_params=None):
      r"""Sets the access control policy on the specified note or occurrence.
Requires `containeranalysis.notes.setIamPolicy` or
`containeranalysis.occurrences.setIamPolicy` permission if the resource is
a note or an occurrence, respectively.

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsNotesSetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('SetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    SetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}:setIamPolicy',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.notes.setIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:setIamPolicy',
        request_field=u'setIamPolicyRequest',
        request_type_name=u'ContaineranalysisProjectsNotesSetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def TestIamPermissions(self, request, global_params=None):
      r"""Returns the permissions that a caller has on the specified note or.
occurrence. Requires list permission on the project (for example,
`containeranalysis.notes.list`).

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsNotesTestIamPermissionsRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (TestIamPermissionsResponse) The response message.
      """
      config = self.GetMethodConfig('TestIamPermissions')
      return self._RunMethod(
          config, request, global_params=global_params)

    TestIamPermissions.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/notes/{notesId}:testIamPermissions',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.notes.testIamPermissions',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:testIamPermissions',
        request_field=u'testIamPermissionsRequest',
        request_type_name=u'ContaineranalysisProjectsNotesTestIamPermissionsRequest',
        response_type_name=u'TestIamPermissionsResponse',
        supports_download=False,
    )

  class ProjectsOccurrencesService(base_api.BaseApiService):
    """Service class for the projects_occurrences resource."""

    _NAME = u'projects_occurrences'

    def __init__(self, client):
      super(ContaineranalysisV1.ProjectsOccurrencesService, self).__init__(client)
      self._upload_configs = {
          }

    def BatchCreate(self, request, global_params=None):
      r"""Creates new occurrences in batch.

      Args:
        request: (ContaineranalysisProjectsOccurrencesBatchCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (BatchCreateOccurrencesResponse) The response message.
      """
      config = self.GetMethodConfig('BatchCreate')
      return self._RunMethod(
          config, request, global_params=global_params)

    BatchCreate.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences:batchCreate',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.occurrences.batchCreate',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[],
        relative_path=u'v1/{+parent}/occurrences:batchCreate',
        request_field=u'batchCreateOccurrencesRequest',
        request_type_name=u'ContaineranalysisProjectsOccurrencesBatchCreateRequest',
        response_type_name=u'BatchCreateOccurrencesResponse',
        supports_download=False,
    )

    def Create(self, request, global_params=None):
      r"""Creates a new occurrence.

      Args:
        request: (ContaineranalysisProjectsOccurrencesCreateRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Occurrence) The response message.
      """
      config = self.GetMethodConfig('Create')
      return self._RunMethod(
          config, request, global_params=global_params)

    Create.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.occurrences.create',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[],
        relative_path=u'v1/{+parent}/occurrences',
        request_field=u'occurrence',
        request_type_name=u'ContaineranalysisProjectsOccurrencesCreateRequest',
        response_type_name=u'Occurrence',
        supports_download=False,
    )

    def Delete(self, request, global_params=None):
      r"""Deletes the specified occurrence. For example, use this method to delete an.
occurrence when the occurrence is no longer applicable for the given
resource.

      Args:
        request: (ContaineranalysisProjectsOccurrencesDeleteRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Empty) The response message.
      """
      config = self.GetMethodConfig('Delete')
      return self._RunMethod(
          config, request, global_params=global_params)

    Delete.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}',
        http_method=u'DELETE',
        method_id=u'containeranalysis.projects.occurrences.delete',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsOccurrencesDeleteRequest',
        response_type_name=u'Empty',
        supports_download=False,
    )

    def Get(self, request, global_params=None):
      r"""Gets the specified occurrence.

      Args:
        request: (ContaineranalysisProjectsOccurrencesGetRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Occurrence) The response message.
      """
      config = self.GetMethodConfig('Get')
      return self._RunMethod(
          config, request, global_params=global_params)

    Get.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.occurrences.get',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsOccurrencesGetRequest',
        response_type_name=u'Occurrence',
        supports_download=False,
    )

    def GetIamPolicy(self, request, global_params=None):
      r"""Gets the access control policy for a note or an occurrence resource.
Requires `containeranalysis.notes.setIamPolicy` or
`containeranalysis.occurrences.setIamPolicy` permission if the resource is
a note or occurrence, respectively.

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsOccurrencesGetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('GetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    GetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}:getIamPolicy',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.occurrences.getIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:getIamPolicy',
        request_field=u'getIamPolicyRequest',
        request_type_name=u'ContaineranalysisProjectsOccurrencesGetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def GetNotes(self, request, global_params=None):
      r"""Gets the note attached to the specified occurrence. Consumer projects can.
use this method to get a note that belongs to a provider project.

      Args:
        request: (ContaineranalysisProjectsOccurrencesGetNotesRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Note) The response message.
      """
      config = self.GetMethodConfig('GetNotes')
      return self._RunMethod(
          config, request, global_params=global_params)

    GetNotes.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}/notes',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.occurrences.getNotes',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[],
        relative_path=u'v1/{+name}/notes',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsOccurrencesGetNotesRequest',
        response_type_name=u'Note',
        supports_download=False,
    )

    def List(self, request, global_params=None):
      r"""Lists occurrences for the specified project.

      Args:
        request: (ContaineranalysisProjectsOccurrencesListRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (ListOccurrencesResponse) The response message.
      """
      config = self.GetMethodConfig('List')
      return self._RunMethod(
          config, request, global_params=global_params)

    List.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences',
        http_method=u'GET',
        method_id=u'containeranalysis.projects.occurrences.list',
        ordered_params=[u'parent'],
        path_params=[u'parent'],
        query_params=[u'filter', u'pageSize', u'pageToken'],
        relative_path=u'v1/{+parent}/occurrences',
        request_field='',
        request_type_name=u'ContaineranalysisProjectsOccurrencesListRequest',
        response_type_name=u'ListOccurrencesResponse',
        supports_download=False,
    )

    def Patch(self, request, global_params=None):
      r"""Updates the specified occurrence.

      Args:
        request: (ContaineranalysisProjectsOccurrencesPatchRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Occurrence) The response message.
      """
      config = self.GetMethodConfig('Patch')
      return self._RunMethod(
          config, request, global_params=global_params)

    Patch.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}',
        http_method=u'PATCH',
        method_id=u'containeranalysis.projects.occurrences.patch',
        ordered_params=[u'name'],
        path_params=[u'name'],
        query_params=[u'updateMask'],
        relative_path=u'v1/{+name}',
        request_field=u'occurrence',
        request_type_name=u'ContaineranalysisProjectsOccurrencesPatchRequest',
        response_type_name=u'Occurrence',
        supports_download=False,
    )

    def SetIamPolicy(self, request, global_params=None):
      r"""Sets the access control policy on the specified note or occurrence.
Requires `containeranalysis.notes.setIamPolicy` or
`containeranalysis.occurrences.setIamPolicy` permission if the resource is
a note or an occurrence, respectively.

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsOccurrencesSetIamPolicyRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (Policy) The response message.
      """
      config = self.GetMethodConfig('SetIamPolicy')
      return self._RunMethod(
          config, request, global_params=global_params)

    SetIamPolicy.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}:setIamPolicy',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.occurrences.setIamPolicy',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:setIamPolicy',
        request_field=u'setIamPolicyRequest',
        request_type_name=u'ContaineranalysisProjectsOccurrencesSetIamPolicyRequest',
        response_type_name=u'Policy',
        supports_download=False,
    )

    def TestIamPermissions(self, request, global_params=None):
      r"""Returns the permissions that a caller has on the specified note or.
occurrence. Requires list permission on the project (for example,
`containeranalysis.notes.list`).

The resource takes the format `projects/[PROJECT_ID]/notes/[NOTE_ID]` for
notes and `projects/[PROJECT_ID]/occurrences/[OCCURRENCE_ID]` for
occurrences.

      Args:
        request: (ContaineranalysisProjectsOccurrencesTestIamPermissionsRequest) input message
        global_params: (StandardQueryParameters, default: None) global arguments
      Returns:
        (TestIamPermissionsResponse) The response message.
      """
      config = self.GetMethodConfig('TestIamPermissions')
      return self._RunMethod(
          config, request, global_params=global_params)

    TestIamPermissions.method_config = lambda: base_api.ApiMethodInfo(
        flat_path=u'v1/projects/{projectsId}/occurrences/{occurrencesId}:testIamPermissions',
        http_method=u'POST',
        method_id=u'containeranalysis.projects.occurrences.testIamPermissions',
        ordered_params=[u'resource'],
        path_params=[u'resource'],
        query_params=[],
        relative_path=u'v1/{+resource}:testIamPermissions',
        request_field=u'testIamPermissionsRequest',
        request_type_name=u'ContaineranalysisProjectsOccurrencesTestIamPermissionsRequest',
        response_type_name=u'TestIamPermissionsResponse',
        supports_download=False,
    )

  class ProjectsService(base_api.BaseApiService):
    """Service class for the projects resource."""

    _NAME = u'projects'

    def __init__(self, client):
      super(ContaineranalysisV1.ProjectsService, self).__init__(client)
      self._upload_configs = {
          }
