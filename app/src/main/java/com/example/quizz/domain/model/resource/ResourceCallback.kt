package pl.lsisoftware.cinema_domain.model.resource

import com.example.quizz.domain.model.resource.Resource

typealias ResourceCallback<ResourceType> = (resource: Resource<ResourceType>) -> (Unit)
typealias VoidCallback = ResourceCallback<Void>