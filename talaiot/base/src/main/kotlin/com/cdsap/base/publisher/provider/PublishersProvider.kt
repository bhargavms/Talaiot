//package com.cdsap.base.publisher.provider
//
//import com.cdsap.base.publisher.logger.LogTracker
//import com.cdsap.base.publisher.publisher.Publisher
//import com.cdsap.talaiot.TalaiotExtension
//import com.cdsap.talaiot.logger.LogTracker
//import com.cdsap.talaiot.configuration.PublishersConfiguration
//import com.cdsap.talaiot.publisher.*
//import com.cdsap.talaiot.publisher.graphpublisher.GraphPublisherFactoryImpl
//import com.cdsap.talaiot.publisher.pushgateway.PushGatewayFormatter
//import com.cdsap.talaiot.publisher.pushgateway.PushGatewayPublisher
//import com.cdsap.talaiot.publisher.rethinkdb.RethinkDbPublisher
//import com.cdsap.talaiot.publisher.timeline.TimelinePublisher
//import com.cdsap.talaiot.request.SimpleRequest
//import org.gradle.api.Project
//import java.util.concurrent.Executor
//
///**
// * Provides the [Publisher]s defined in the [PublishersConfiguration] of the [TalaiotExtension]
// */
////class PublishersProvider(
////    /**
////     * Gradle Project used to retrieve the extension
////     */
////    val project: Project,
////    val logger: LogTracker,
////    val executor: Executor,
////    val heavyExecutor: Executor
////) : Provider<List<Publisher>> {
////
////    /**
////     * Check the main [TalaiotExtension] which publishers have been enabled.
////     * When one publisher is enabled it initialize it with the required parameters
////     *
////     * @return list of available Publisher for the configuration
////     */
////    override fun get(): List<Publisher> {
////        val publishers = mutableListOf<Publisher>()
////        val talaiotExtension = project.extensions.getByName("talaiot") as TalaiotExtension
////
////        talaiotExtension.publishers?.apply {
////
////            influxDbPublisher?.apply {
////                publishers.add(
////                    InfluxDbPublisher(
////                        this,
////                        logger,
////                        executor
////                    )
////                )
////            }
////            taskDependencyGraphPublisher?.apply {
////                publishers.add(
////                    TaskDependencyGraphPublisher(
////                        this,
////                        logger,
////                        heavyExecutor,
////                        GraphPublisherFactoryImpl()
////                    )
////                )
////            }
////            pushGatewayPublisher?.apply {
////                publishers.add(
////                    PushGatewayPublisher(
////                        this,
////                        logger,
////                        SimpleRequest(logger),
////                        executor,
////                        PushGatewayFormatter()
////                    )
////                )
////            }
////
////
////            elasticSearchPublisher?.apply {
////                publishers.add(
////                    ElasticSearchPublisher(
////                        this,
////                        logger,
////                        executor
////                    )
////                )
////            }
////
////            hybridPublisher?.apply {
////                publishers.add(
////                    HybridPublisher(
////                        this,
////                        logger,
////                        executor
////                    )
////                )
////            }
////
////            rethinkDbPublisher?.apply {
////                publishers.add(
////                    RethinkDbPublisher(
////                        this,
////                        logger,
////                        executor
////                    )
////                )
////            }
////
////            publishers.addAll(customPublishers)
////        }
////        return publishers
////    }
////}